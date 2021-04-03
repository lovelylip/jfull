import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IChiTieu } from '@/shared/model/chi-tieu.model';

import ChiTieuService from './chi-tieu.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class ChiTieu extends Vue {
  @Inject('chiTieuService') private chiTieuService: () => ChiTieuService;
  private removeId: string = null;
  public itemsPerPage = 20;
  public queryCount: number = null;
  public page = 1;
  public previousPage = 1;
  public propOrder = 'id';
  public reverse = false;
  public totalItems = 0;

  public chiTieus: IChiTieu[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllChiTieus();
  }

  public clear(): void {
    this.page = 1;
    this.retrieveAllChiTieus();
  }

  public retrieveAllChiTieus(): void {
    this.isFetching = true;

    const paginationQuery = {
      page: this.page - 1,
      size: this.itemsPerPage,
      sort: this.sort(),
    };
    this.chiTieuService()
      .retrieve(paginationQuery)
      .then(
        res => {
          this.chiTieus = res.data;
          this.totalItems = Number(res.headers['x-total-count']);
          this.queryCount = this.totalItems;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public handleSyncList(): void {
    this.clear();
  }

  public sort(): Array<any> {
    const result = [this.propOrder + ',' + (this.reverse ? 'desc' : 'asc')];
    if (this.propOrder !== 'id') {
      result.push('id');
    }
    return result;
  }

  public loadPage(page: number): void {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  public transition(): void {
    this.retrieveAllChiTieus();
  }

  public changeOrder(propOrder): void {
    this.propOrder = propOrder;
    this.reverse = !this.reverse;
    this.transition();
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
