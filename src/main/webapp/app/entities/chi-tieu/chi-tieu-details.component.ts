import { Component, Vue, Inject } from 'vue-property-decorator';

import { IChiTieu } from '@/shared/model/chi-tieu.model';
import ChiTieuService from './chi-tieu.service';

@Component
export default class ChiTieuDetails extends Vue {
  @Inject('chiTieuService') private chiTieuService: () => ChiTieuService;
  public chiTieu: IChiTieu = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.chiTieuId) {
        vm.retrieveChiTieu(to.params.chiTieuId);
      }
    });
  }

  public retrieveChiTieu(chiTieuId) {
    this.chiTieuService()
      .find(chiTieuId)
      .then(res => {
        this.chiTieu = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
