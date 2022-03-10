/**
 * @Description: -TODO
 * @Author ZhangWei
 * @Date   2021/7/13 17:25
 * @Version V1.0
 */
import React from 'react';
import { message, Popconfirm} from "antd";
import {Button as ElButton} from "element-react";
import companyApi from "../../http/api/CompanyApi";

export default function DeleteCompany(props) {


    async function deleteAdmin() {
        await companyApi.delete(props.id);
        message.success("删除成功");
        //父组件删除后续动作
        props.handleDelete()
    }

    function handleCancel() {
        message.info('取消删除')
    }


  return (
      <Popconfirm  title='删除企业'
                   onConfirm={deleteAdmin}
                   onCancel={handleCancel}
      >
          <ElButton type="danger">删除</ElButton>
      </Popconfirm>
  );
}

