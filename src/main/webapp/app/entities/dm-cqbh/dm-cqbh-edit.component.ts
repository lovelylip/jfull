import { Component, Inject, Vue } from 'vue-property-decorator';

import { DmCqbh, IDmCqbh } from '@/shared/model/dm-cqbh.model';
import DmCqbhService from './dm-cqbh.service';
import { email, maxLength, minLength, required } from 'vuelidate/lib/validators';

const validations: any = {
  dmCqbh: {
    ma: {
      required,
      maxLength: maxLength(10),
    },
    ten: {
      required,
      maxLength: maxLength(150),
    },
    maCha: {
      required,
      maxLength: maxLength(10),
    },
    tenCha: {
      maxLength: maxLength(150),
    },
  },
};

@Component({
  validations,
})
export default class DmCqbhEdit extends Vue {
  @Inject('dmCqbhService') private dmCqbhService: () => DmCqbhService;
  public dmCqbh: IDmCqbh = {};
  public isSaving = false;
  public authorities: any[] = [];
  public languages: any = this.$store.getters.languages;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.dmCqbhId) {
        vm.retrieveDmCqbh(to.params.dmCqbhId);
      }
    });
  }

  public constructor() {
    super();
    this.dmCqbh = new DmCqbh();
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

  public save(): void {
    this.isSaving = true;
    if (this.dmCqbh.id) {
      this.dmCqbhService()
        .update(this.dmCqbh)
        .then(res => {
          this.returnToList();
          this.$root.$bvToast.toast(this.getMessageFromHeader(res).toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.dmCqbhService()
        .create(this.dmCqbh)
        .then(res => {
          this.returnToList();
          this.$root.$bvToast.toast(this.getMessageFromHeader(res).toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Success',
            variant: 'success',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    }
  }

  private returnToList(): void {
    this.isSaving = false;
    (<any>this).$router.go(-1);
  }

  private getMessageFromHeader(res: any): any {
    return this.$t(res.headers['x-gatewayapp-alert'], {
      param: decodeURIComponent(res.headers['x-gatewayapp-params'].replace(/\+/g, ' ')),
    });
  }
}
