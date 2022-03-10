/**
 * @Description: -TODO
 * @Author ZhangWei
 * @Date   2021/7/9 22:20
 * @Version V1.0
 */
import axios, { AxiosResponse, Method} from 'axios';
import {message} from "antd";

export interface ResponseDto {
    message: string;
    success: boolean;
    data: any;
}

export interface RequestDto {
    url: string,
    headers?: object,
    params?: object,
    data?: object,
    method: Method,
    failFunc? :Function,
}

let http = axios.create({
    timeout: 15000, // request timeout
    baseURL: process.env.REACT_APP_BASE_URL
});

export function baseApi(reqDto: RequestDto): Promise<ResponseDto> {
    let {url, headers, params, data, method, failFunc} = reqDto;

    return new Promise<ResponseDto>((resolve, reject) => {
        http({
            url,
            method,
            //token的数据头
            headers,
            //路径带参数 **/？+参数
            params,
            //responsebody里面的参数，body{****}
            data,
        })//async为异步代码
            .then(async (res:AxiosResponse)=>{
                let data: ResponseDto = res.data;
                if (data.success) {
                    resolve(data.data)
                }else{
                    message.error(data.message);
                    if (failFunc !== undefined) {
                        failFunc()
                    }
                }
            })
    });
}
