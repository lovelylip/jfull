import { Component, Vue, Inject } from 'vue-property-decorator';

import { IDmCqbh } from '@/shared/model/dm-cqbh.model';
import DmCqbhService from './dm-cqbh.service';

@Component
export default class DmCqbhDetails extends Vue {
  @Inject('dmCqbhService') private dmCqbhService: () => DmCqbhService;
  public dmCqbh: IDmCqbh = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.dmCqbhId) {
        vm.retrieveDmCqbh(to.params.dmCqbhId);
      }
    });
  }

  public retrieveDmCqbh(dmCqbhId) {
    this.dmCqbhService()
      .find(dmCqbhId)
      .then(res => {
        this.dmCqbh = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
