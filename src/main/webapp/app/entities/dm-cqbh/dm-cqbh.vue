<template>
  <div>
    <h2 id="page-heading" data-cy="DmCqbhHeading">
      <span v-text="$t('gatewayApp.dmCqbh.home.title')" id="dm-cqbh-heading">Dm Cqbhs</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('gatewayApp.dmCqbh.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link custom v-slot="{ navigate }" :to="{ name: 'DmCqbhNew' }">
          <button @click="navigate" class="btn btn-primary jh-create-entity">
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('gatewayApp.dmCqbh.home.createLabel')">Create a new User</span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && dmCqbhs && dmCqbhs.length === 0">
      <span v-text="$t('gatewayApp.dmCqbh.home.notFound')">No dmCqbhs found</span>
    </div>
    <div class="table-responsive" v-if="dmCqbhs && dmCqbhs.length > 0">
      <table class="table table-striped" aria-describedby="dmCqbhs">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="$t('global.field.id')">ID</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('ma')">
              <span v-text="$t('gatewayApp.dmCqbh.ma')">Ma</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'ma'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('ten')">
              <span v-text="$t('gatewayApp.dmCqbh.ten')">Ten</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'ten'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('maCha')">
              <span v-text="$t('gatewayApp.dmCqbh.maCha')">Ma Cha</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'maCha'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('tenCha')">
              <span v-text="$t('gatewayApp.dmCqbh.tenCha')">Ten Cha</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'tenCha'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="dmCqbh in dmCqbhs" :key="dmCqbh.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'DmCqbhView', params: { dmCqbhId: dmCqbh.id } }">{{ dmCqbh.id }}</router-link>
            </td>
            <td>{{ dmCqbh.ma }}</td>
            <td>{{ dmCqbh.ten }}</td>
            <td>{{ dmCqbh.maCha }}</td>
            <td>{{ dmCqbh.tenCha }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'DmCqbhView', params: { dmCqbhId: dmCqbh.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'DmCqbhEdit', params: { dmCqbhId: dmCqbh.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button v-on:click="prepareRemove(dmCqbh)" variant="danger" class="btn btn-sm delete"
                  ><!-- :disabled="username === user.login"-->
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span id="gatewayApp.dmCqbh.delete.question" data-cy="dmCqbhDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-dmCqbh-heading" v-text="$t('gatewayApp.dmCqbh.delete.question', { id: removeId })">
          Are you sure you want to delete this Dm Cqbh?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-dmCqbh"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeDmCqbh()"
        >
          Delete
        </button>
      </div>
    </b-modal>
    <div v-show="dmCqbhs && dmCqbhs.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./dm-cqbh.component.ts"></script>
