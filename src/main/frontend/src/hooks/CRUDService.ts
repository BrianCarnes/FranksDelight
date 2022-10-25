import Response from './response';
import axios from 'axios';

export default class BaseService {
  private static baseURL: string = 'https://franks.fulgentcorp.com:8080/api';

  public static async getAll<T>(url: string): Promise<Response> {
    return await axios
      .get<Array<T>>(this.baseURL + url)
      .then((response: any) => {
        const result = response.data;
        if (result && response.status === 200) {
          return new Response(true, result.data as Array<T>, 'Success', '');
        } else {
          const msg =
            result.messageList && result.messageList.length > 0
              ? result.messageList[0].text
              : 'Error';
          return new Response(false, null, 'Error', msg);
        }
      })
      .catch(function (error) {
        return new Response(false, null, 'Error', error);
      });
  }

  public static get<T>(url: string, param: any): Promise<Response> {
    return axios
      .get<T>(this.baseURL + url + param)
      .then((response: any) => {
        const result = response.data;
        if (result && result.success) {
          return new Response(true, result.data, 'Success', '');
        } else {
          const msg =
            result.messageList && result.messageList.length > 0
              ? result.messageList[0].text
              : 'Error';
          return new Response(false, null, 'Error', msg);
        }
      })
      .catch(function (error) {
        return new Response(false, null, 'Error', error);
      });
  }
  public static delete(url: string, param: any): Promise<Response> {
    console.log(param);

    return axios
      .delete(this.baseURL + url, { data: param })
      .then((response) => {
        const result = response.data;
        if (result && result.success) {
          return new Response(true, result.data, 'Success', '');
        } else {
          const msg =
            result.messageList && result.messageList.length > 0
              ? result.messageList[0].text
              : 'Error';
          return new Response(false, null, 'Error', msg);
        }
      })
      .catch(function (error) {
        return new Response(false, null, 'Error', error);
      });
  }
  public static create<T>(url: string, obj: T): Promise<Response> {
    return axios
      .post(this.baseURL + url, obj)
      .then((response) => {
        const result = response.data;
        if (result && result.success) {
          return new Response(true, result.data, 'Success', '');
        } else {
          const msg =
            result.messageList && result.messageList.length > 0
              ? result.messageList[0].text
              : 'Error';
          return new Response(false, null, 'Error', msg);
        }
      })
      .catch(function (error) {
        return new Response(false, null, 'Error', error);
      });
  }
  public static update<T>(url: string, obj: T): Promise<Response> {
    return axios
      .patch(this.baseURL + url, obj)
      .then((response) => {
        const result = response.data;
        if (response && response.status === 200) {
          return new Response(true, result.data, 'Success', '');
        } else {
          const msg =
            result.messageList && result.messageList.length > 0
              ? result.messageList[0].text
              : 'Error';
          return new Response(false, null, 'Error', msg);
        }
      })
      .catch(function (error) {
        return new Response(false, null, 'Error', error);
      });
  }
}
