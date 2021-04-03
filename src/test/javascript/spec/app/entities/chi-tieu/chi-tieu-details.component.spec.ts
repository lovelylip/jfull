/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import ChiTieuDetailComponent from '@/entities/chi-tieu/chi-tieu-details.vue';
import ChiTieuClass from '@/entities/chi-tieu/chi-tieu-details.component';
import ChiTieuService from '@/entities/chi-tieu/chi-tieu.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('ChiTieu Management Detail Component', () => {
    let wrapper: Wrapper<ChiTieuClass>;
    let comp: ChiTieuClass;
    let chiTieuServiceStub: SinonStubbedInstance<ChiTieuService>;

    beforeEach(() => {
      chiTieuServiceStub = sinon.createStubInstance<ChiTieuService>(ChiTieuService);

      wrapper = shallowMount<ChiTieuClass>(ChiTieuDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { chiTieuService: () => chiTieuServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundChiTieu = { id: 'ABC' };
        chiTieuServiceStub.find.resolves(foundChiTieu);

        // WHEN
        comp.retrieveChiTieu('ABC');
        await comp.$nextTick();

        // THEN
        expect(comp.chiTieu).toBe(foundChiTieu);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundChiTieu = { id: 'ABC' };
        chiTieuServiceStub.find.resolves(foundChiTieu);

        // WHEN
        comp.beforeRouteEnter({ params: { chiTieuId: 'ABC' } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.chiTieu).toBe(foundChiTieu);
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
