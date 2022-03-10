/**
 * @Description: -TODO
 * @Author ZhangWei
 * @Date   2021/7/11 20:11
 * @Version V1.0
 */
import {baseApi, RequestDto, ResponseDto} from "../Axiosconfig";
import {CompanyEntity} from "../../entity";

function addApi(data: CompanyEntity) :Promise<ResponseDto>{
    let reqDto: RequestDto = {
        url : 'company',
        method : 'POST',
        data
    };
    return baseApi(reqDto);
}

function editApi(data: CompanyEntity) :Promise<ResponseDto>{
    let reqDto: RequestDto = {
        url : 'company',
        method : 'PUT',
        data
    };
    return baseApi(reqDto);
}

function listApi(){
    let reqDto: RequestDto = {
        url : 'company',
        method : 'GET',
    };
    return baseApi(reqDto);
}

function deleteApi(id:string){
    let reqDto: RequestDto = {
        url : 'company/'+id,
        method : 'DELETE',
    };
    return baseApi(reqDto);
}

let companyApi = {
    add : (data: CompanyEntity)=> addApi(data),
    list : listApi,
    delete : (id:string)=> deleteApi(id),
    edit : (data : CompanyEntity) => editApi(data)
};

 export default companyApi
