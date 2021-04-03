export interface IChiTieu {
  id?: string;
  type?: string;
  noiDung?: string;
  tien?: number;
  ngayChiTieu?: Date;
  ghiChu?: string | null;
}

export class ChiTieu implements IChiTieu {
  constructor(
    public id?: string,
    public type?: string,
    public noiDung?: string,
    public tien?: number,
    public ngayChiTieu?: Date,
    public ghiChu?: string | null
  ) {}
}
