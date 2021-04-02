<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="dmCqbh">
        <h2 class="jh-entity-heading" data-cy="dmCqbhDetailsHeading">
          <span v-text="$t('gatewayApp.dmCqbh.detail.title')">DmCqbh</span> {{ dmCqbh.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('gatewayApp.dmCqbh.ma')">Ma</span>
          </dt>
          <dd>
            <span>{{ dmCqbh.ma }}</span>
          </dd>
          <dt>
            <span v-text="$t('gatewayApp.dmCqbh.ten')">Ten</span>
          </dt>
          <dd>
            <span>{{ dmCqbh.ten }}</span>
          </dd>
          <dt>
            <span v-text="$t('gatewayApp.dmCqbh.maCha')">Ma Cha</span>
          </dt>
          <dd>
            <span>{{ dmCqbh.maCha }}</span>
          </dd>
          <dt>
            <span v-text="$t('gatewayApp.dmCqbh.tenCha')">Ten Cha</span>
          </dt>
          <dd>
            <span>{{ dmCqbh.tenCha }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./dm-cqbh-details.component.ts"></script>
