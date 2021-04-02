export interface IDmCqbh {
  id?: string;
  ma?: string;
  ten?: string;
  maCha?: string;
  tenCha?: string | null;
}

export class DmCqbh implements IDmCqbh {
  constructor(public id?: string, public ma?: string, public ten?: string, public maCha?: string, public tenCha?: string | null) {}
}
