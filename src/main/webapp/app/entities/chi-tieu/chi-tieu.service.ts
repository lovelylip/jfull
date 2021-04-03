import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { IChiTieu } from '@/shared/model/chi-tieu.model';

const baseApiUrl = 'api/chi-tieus';

export default class ChiTieuService {
  public find(id: string): Promise<IChiTieu> {
    return new Promise<IChiTieu>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${id}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public retrieve(paginationQuery?: any): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(baseApiUrl + `?${buildPaginationQueryOpts(paginationQuery)}`)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
