<template>
  <div>
    <h2 id="page-heading" data-cy="ChiTieuHeading">
      <span v-text="$t('gatewayApp.chiTieu.home.title')" id="chi-tieu-heading">Chi Tieus</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('gatewayApp.chiTieu.home.refreshListLabel')">Refresh List</span>
        </button>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && chiTieus && chiTieus.length === 0">
      <span v-text="$t('gatewayApp.chiTieu.home.notFound')">No chiTieus found</span>
    </div>
    <div class="table-responsive" v-if="chiTieus && chiTieus.length > 0">
      <table class="table table-striped" aria-describedby="chiTieus">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="$t('global.field.id')">ID</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('type')">
              <span v-text="$t('gatewayApp.chiTieu.type')">Type</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'type'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('noiDung')">
              <span v-text="$t('gatewayApp.chiTieu.noiDung')">Noi Dung</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'noiDung'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('tien')">
              <span v-text="$t('gatewayApp.chiTieu.tien')">Tien</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'tien'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('ngayChiTieu')">
              <span v-text="$t('gatewayApp.chiTieu.ngayChiTieu')">Ngay Chi Tieu</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'ngayChiTieu'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('ghiChu')">
              <span v-text="$t('gatewayApp.chiTieu.ghiChu')">Ghi Chu</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'ghiChu'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="chiTieu in chiTieus" :key="chiTieu.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ChiTieuView', params: { chiTieuId: chiTieu.id } }">{{ chiTieu.id }}</router-link>
            </td>
            <td>{{ chiTieu.type }}</td>
            <td>{{ chiTieu.noiDung }}</td>
            <td>{{ chiTieu.tien }}</td>
            <td>{{ chiTieu.ngayChiTieu ? $d(Date.parse(chiTieu.ngayChiTieu), 'short') : '' }}</td>
            <td>{{ chiTieu.ghiChu }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ChiTieuView', params: { chiTieuId: chiTieu.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span id="gatewayApp.chiTieu.delete.question" data-cy="chiTieuDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-chiTieu-heading" v-text="$t('gatewayApp.chiTieu.delete.question', { id: removeId })">
          Are you sure you want to delete this Chi Tieu?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-chiTieu"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeChiTieu()"
        >
          Delete
        </button>
      </div>
    </b-modal>
    <div v-show="chiTieus && chiTieus.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./chi-tieu.component.ts"></script>
