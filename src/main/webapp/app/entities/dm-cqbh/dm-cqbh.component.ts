import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IDmCqbh } from '@/shared/model/dm-cqbh.model';

import DmCqbhService from './dm-cqbh.service';
import TreeTable from 'primevue/treetable';
import Column from 'primevue/column';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class DmCqbh extends Vue {
  @Inject('dmCqbhService') private dmCqbhService: () => DmCqbhService;
  private removeId: string = null;
  public itemsPerPage = 20;
  public queryCount: number = null;
  public page = 1;
  public previousPage = 1;
  public propOrder = 'id';
  public reverse = false;
  public totalItems = 0;

  public dmCqbhs: IDmCqbh[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllDmCqbhs();
  }

  public clear(): void {
    this.page = 1;
    this.retrieveAllDmCqbhs();
  }

  public retrieveAllDmCqbhs(): void {
    this.isFetching = true;

    const paginationQuery = {
      page: this.page - 1,
      size: this.itemsPerPage,
      sort: this.sort(),
    };
    this.dmCqbhService()
      .retrieve(paginationQuery)
      .then(
        res => {
          this.dmCqbhs = res.data;
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
    this.retrieveAllDmCqbhs();
  }

  public changeOrder(propOrder): void {
    this.propOrder = propOrder;
    this.reverse = !this.reverse;
    this.transition();
  }

  public deleteUser(): void {
    this.dmCqbhService()
      .remove(this.removeId)
      .then(res => {
        const message = this.$t(res.headers['x-gatewayapp-alert'], {
          param: decodeURIComponent(res.headers['x-gatewayapp-params'].replace(/\+/g, ' ')),
        });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllDmCqbhs();
        this.closeDialog();
      });
  }

  public prepareRemove(instance): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
