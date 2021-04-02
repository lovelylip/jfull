/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import DmCqbhComponent from '@/entities/dm-cqbh/dm-cqbh.vue';
import DmCqbhClass from '@/entities/dm-cqbh/dm-cqbh.component';
import DmCqbhService from '@/entities/dm-cqbh/dm-cqbh.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-badge', {});
localVue.component('jhi-sort-indicator', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('DmCqbh Management Component', () => {
    let wrapper: Wrapper<DmCqbhClass>;
    let comp: DmCqbhClass;
    let dmCqbhServiceStub: SinonStubbedInstance<DmCqbhService>;

    beforeEach(() => {
      dmCqbhServiceStub = sinon.createStubInstance<DmCqbhService>(DmCqbhService);
      dmCqbhServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<DmCqbhClass>(DmCqbhComponent, {
        store,
        i18n,
        localVue,
        stubs: { jhiItemCount: true, bPagination: true, bModal: bModalStub as any },
        provide: {
          dmCqbhService: () => dmCqbhServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      dmCqbhServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

      // WHEN
      comp.retrieveAllDmCqbhs();
      await comp.$nextTick();

      // THEN
      expect(dmCqbhServiceStub.retrieve.called).toBeTruthy();
      expect(comp.dmCqbhs[0]).toEqual(jasmine.objectContaining({ id: 'ABC' }));
    });

    it('should load a page', async () => {
      // GIVEN
      dmCqbhServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });
      comp.previousPage = 1;

      // WHEN
      comp.loadPage(2);
      await comp.$nextTick();

      // THEN
      expect(dmCqbhServiceStub.retrieve.called).toBeTruthy();
      expect(comp.dmCqbhs[0]).toEqual(jasmine.objectContaining({ id: 'ABC' }));
    });

    it('should not load a page if the page is the same as the previous page', () => {
      // GIVEN
      dmCqbhServiceStub.retrieve.reset();
      comp.previousPage = 1;

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(dmCqbhServiceStub.retrieve.called).toBeFalsy();
    });

    it('should re-initialize the page', async () => {
      // GIVEN
      dmCqbhServiceStub.retrieve.reset();
      dmCqbhServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

      // WHEN
      comp.loadPage(2);
      await comp.$nextTick();
      comp.clear();
      await comp.$nextTick();

      // THEN
      expect(dmCqbhServiceStub.retrieve.callCount).toEqual(3);
      expect(comp.page).toEqual(1);
      expect(comp.dmCqbhs[0]).toEqual(jasmine.objectContaining({ id: 'ABC' }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,asc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // GIVEN
      comp.propOrder = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,asc', 'id']);
    });
  });
});
