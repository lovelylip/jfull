/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import { DATE_TIME_FORMAT } from '@/shared/date/filters';
import ChiTieuService from '@/entities/chi-tieu/chi-tieu.service';
import { ChiTieu } from '@/shared/model/chi-tieu.model';

const error = {
  response: {
    status: null,
    data: {
      type: null,
    },
  },
};

const axiosStub = {
  get: sinon.stub(axios, 'get'),
  post: sinon.stub(axios, 'post'),
  put: sinon.stub(axios, 'put'),
  patch: sinon.stub(axios, 'patch'),
  delete: sinon.stub(axios, 'delete'),
};

describe('Service Tests', () => {
  describe('ChiTieu Service', () => {
    let service: ChiTieuService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new ChiTieuService();
      currentDate = new Date();
      elemDefault = new ChiTieu('ID', 'AAAAAAA', 'AAAAAAA', 0, currentDate, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            ngayChiTieu: dayjs(currentDate).format(DATE_TIME_FORMAT),
          },
          elemDefault
        );
        axiosStub.get.resolves({ data: returnedFromService });

        return service.find('ABC').then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });

      it('should not find an element', async () => {
        axiosStub.get.rejects(error);
        return service
          .find('ABC')
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of ChiTieu', async () => {
        const returnedFromService = Object.assign(
          {
            type: 'BBBBBB',
            noiDung: 'BBBBBB',
            tien: 1,
            ngayChiTieu: dayjs(currentDate).format(DATE_TIME_FORMAT),
            ghiChu: 'BBBBBB',
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            ngayChiTieu: currentDate,
          },
          returnedFromService
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of ChiTieu', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });
    });
  });
});
