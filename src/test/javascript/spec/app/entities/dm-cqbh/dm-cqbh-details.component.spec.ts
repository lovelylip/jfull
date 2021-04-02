/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import DmCqbhDetailComponent from '@/entities/dm-cqbh/dm-cqbh-details.vue';
import DmCqbhClass from '@/entities/dm-cqbh/dm-cqbh-details.component';
import DmCqbhService from '@/entities/dm-cqbh/dm-cqbh.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('DmCqbh Management Detail Component', () => {
    let wrapper: Wrapper<DmCqbhClass>;
    let comp: DmCqbhClass;
    let dmCqbhServiceStub: SinonStubbedInstance<DmCqbhService>;

    beforeEach(() => {
      dmCqbhServiceStub = sinon.createStubInstance<DmCqbhService>(DmCqbhService);

      wrapper = shallowMount<DmCqbhClass>(DmCqbhDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { dmCqbhService: () => dmCqbhServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundDmCqbh = { id: 'ABC' };
        dmCqbhServiceStub.find.resolves(foundDmCqbh);

        // WHEN
        comp.retrieveDmCqbh('ABC');
        await comp.$nextTick();

        // THEN
        expect(comp.dmCqbh).toBe(foundDmCqbh);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundDmCqbh = { id: 'ABC' };
        dmCqbhServiceStub.find.resolves(foundDmCqbh);

        // WHEN
        comp.beforeRouteEnter({ params: { dmCqbhId: 'ABC' } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.dmCqbh).toBe(foundDmCqbh);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        comp.previousState();
        await comp.$nextTick();

        expect(comp.$router.currentRoute.fullPath).toContain('/');
      });
    });
  });
});
