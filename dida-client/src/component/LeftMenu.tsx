/**
 * @Description: -TODO
 * @Author ZhangWei
 * @Date   2021/6/28 17:30
 * @Version V1.0
 */

import { Menu } from 'antd';
import React,{Fragment} from 'react';

import {IRoute, router} from "../router";
import {NavLink} from "react-router-dom";

function generateMenu(routerList: Array<IRoute>) {

  return (
      <Fragment>
        {
          routerList?.map((route) => {
            if (route.children) {
              return (
                  <Menu.SubMenu
                      key={route.id}
                      title={route.title}
                      icon={route.icon}
                  >
                    {generateMenu(route.children)}
                  </Menu.SubMenu>
              )
            } else {
              return (
                  <Menu.Item key={route.id} icon={route.icon}>
                    <NavLink to={route.path}>{route.title}</NavLink>
                  </Menu.Item>
              )
            }
          })


        }
      </Fragment>
  )


}

export default function LeftMenu() {

  return (
      <Fragment>
        <div className="logo"/>
        <Menu theme="dark" mode="inline" defaultSelectedKeys={['1']}>
          {generateMenu(router)}
        </Menu>
      </Fragment>
  );
}

