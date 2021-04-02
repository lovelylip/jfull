import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore

// prettier-ignore
const DmCqbh = () => import('@/entities/dm-cqbh/dm-cqbh.vue');
// prettier-ignore
const DmCqbhDetails = () => import('@/entities/dm-cqbh/dm-cqbh-details.vue');
const DmCqbhEdit = () => import('@/entities/dm-cqbh/dm-cqbh-edit.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default [
  {
    path: '/dm-cqbh',
    name: 'DmCqbh',
    component: DmCqbh,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/dm-cqbh/:dmCqbhId/view',
    name: 'DmCqbhView',
    component: DmCqbhDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/dm-cqbh/:dmCqbhId/edit',
    name: 'DmCqbhEdit',
    component: DmCqbhEdit,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/dm-cqbh/new',
    name: 'DmCqbhNew',
    component: DmCqbhEdit,
    meta: { authorities: [Authority.USER] },
  },
  // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
];
