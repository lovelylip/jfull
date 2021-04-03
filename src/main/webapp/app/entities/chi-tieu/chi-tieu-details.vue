<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="chiTieu">
        <h2 class="jh-entity-heading" data-cy="chiTieuDetailsHeading">
          <span v-text="$t('gatewayApp.chiTieu.detail.title')">ChiTieu</span> {{ chiTieu.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('gatewayApp.chiTieu.type')">Type</span>
          </dt>
          <dd>
            <span>{{ chiTieu.type }}</span>
          </dd>
          <dt>
            <span v-text="$t('gatewayApp.chiTieu.noiDung')">Noi Dung</span>
          </dt>
          <dd>
            <span>{{ chiTieu.noiDung }}</span>
          </dd>
          <dt>
            <span v-text="$t('gatewayApp.chiTieu.tien')">Tien</span>
          </dt>
          <dd>
            <span>{{ chiTieu.tien }}</span>
          </dd>
          <dt>
            <span v-text="$t('gatewayApp.chiTieu.ngayChiTieu')">Ngay Chi Tieu</span>
          </dt>
          <dd>
            <span v-if="chiTieu.ngayChiTieu">{{ $d(Date.parse(chiTieu.ngayChiTieu), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('gatewayApp.chiTieu.ghiChu')">Ghi Chu</span>
          </dt>
          <dd>
            <span>{{ chiTieu.ghiChu }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./chi-tieu-details.component.ts"></script>
