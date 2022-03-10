import {baseApi} from "../Axiosconfig";
import {UserEntity} from "../../entity";
import {ResponseDto} from "../Axiosconfig";

function listApi(){
    let reqDto = {
        url : 'user',
        method : 'GET',
    };
    return baseApi(reqDto);
}

function addApi(data){
    let reqDto ={
        url: 'user',
        method: 'POST',
        data
    };
    return baseApi(reqDto);
}
function editApi(data){
    let reqDto ={
        url: 'user',
        method: 'PUT',
        data
    };
    return baseApi(reqDto);
}

let userApi = {
    list : ()=>listApi(),
    add : (data)=>addApi(data),
    edit : (data)=>editApi(data)
};

export default userApi
