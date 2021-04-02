import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { IDmCqbh } from '@/shared/model/dm-cqbh.model';

const baseApiUrl = 'api/dm-cqbhs';

export default class DmCqbhService {
  public create(dmCqbh): Promise<any> {
    return axios.post(baseApiUrl, dmCqbh);
  }

  public update(dmCqbh): Promise<any> {
    return axios.put(baseApiUrl, dmCqbh);
  }

  public remove(id: string): Promise<any> {
    return axios.delete(`${baseApiUrl}/${id}`);
  }

  public find(id: string): Promise<IDmCqbh> {
    return new Promise<IDmCqbh>((resolve, reject) => {
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
