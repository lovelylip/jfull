<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" v-if="dmCqbh">
        <h2 id="myUserLabel" v-text="$t('userManagement.home.createOrEditLabel')">Create or edit a User</h2>
        <div>
          <div class="form-group" :hidden="!dmCqbh.id">
            <label v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" name="id" v-model="dmCqbh.id" readonly />
          </div>

          <div class="form-group">
            <label class="form-control-label" for="ma" v-text="$t('gatewayApp.dmCqbh.ma')">First Name</label>
            <input
              type="text"
              class="form-control"
              id="ma"
              name="ma"
              v-bind:placeholder="$t('gatewayApp.dmCqbh.maHolder')"
              :class="{ valid: !$v.dmCqbh.ma.$invalid, invalid: $v.dmCqbh.ma.$invalid }"
              v-model="$v.dmCqbh.ma.$model"
            />
            <div v-if="$v.dmCqbh.ma.$anyDirty && $v.dmCqbh.ma.$invalid">
              <small class="form-text text-danger" v-if="!$v.dmCqbh.ma.maxLength" v-text="$t('entity.validation.maxlength', { max: 10 })">
                This field cannot be longer than 10 characters.
              </small>
            </div>
          </div>

          <div class="form-group">
            <label class="form-control-label" for="ten" v-text="$t('gatewayApp.dmCqbh.ten')">First Name</label>
            <input
              type="text"
              class="form-control"
              id="ten"
              name="ten"
              v-bind:placeholder="$t('gatewayApp.dmCqbh.tenHolder')"
              :class="{ valid: !$v.dmCqbh.ten.$invalid, invalid: $v.dmCqbh.ten.$invalid }"
              v-model="$v.dmCqbh.ten.$model"
            />
            <div v-if="$v.dmCqbh.ten.$anyDirty && $v.dmCqbh.ten.$invalid">
              <small class="form-text text-danger" v-if="!$v.dmCqbh.ten.maxLength" v-text="$t('entity.validation.maxlength', { max: 150 })">
                This field cannot be longer than 150 characters.
              </small>
            </div>
          </div>

          <div class="form-group">
            <label class="form-control-label" for="maCha" v-text="$t('gatewayApp.dmCqbh.maCha')">First Name</label>
            <input
              type="text"
              class="form-control"
              id="maCha"
              name="maCha"
              v-bind:placeholder="$t('gatewayApp.dmCqbh.maChaHolder')"
              :class="{ valid: !$v.dmCqbh.maCha.$invalid, invalid: $v.dmCqbh.maCha.$invalid }"
              v-model="$v.dmCqbh.maCha.$model"
            />
            <div v-if="$v.dmCqbh.maCha.$anyDirty && $v.dmCqbh.maCha.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.dmCqbh.maCha.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 10 })"
              >
                This field cannot be longer than 10 characters.
              </small>
            </div>
          </div>

          <div class="form-group">
            <label class="form-control-label" for="tenCha" v-text="$t('gatewayApp.dmCqbh.tenCha')">First Name</label>
            <input
              type="text"
              class="form-control"
              id="tenCha"
              name="tenCha"
              v-bind:placeholder="$t('gatewayApp.dmCqbh.tenChaHolder')"
              :class="{ valid: !$v.dmCqbh.tenCha.$invalid, invalid: $v.dmCqbh.tenCha.$invalid }"
              v-model="$v.dmCqbh.tenCha.$model"
            />
            <div v-if="$v.dmCqbh.tenCha.$anyDirty && $v.dmCqbh.tenCha.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.dmCqbh.tenCha.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 150 })"
              >
                This field cannot be longer than 150 characters.
              </small>
            </div>
          </div>
        </div>
        <div>
          <button type="button" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button type="submit" :disabled="$v.dmCqbh.$invalid || isSaving" class="btn btn-primary">
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>

<script lang="ts" src="./dm-cqbh-edit.component.ts"></script>
