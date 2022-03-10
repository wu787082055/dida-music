/**
 * @Description: -TODO
 * @Author ZhangWei
 * @Date   2021/6/28 15:07
 * @Version V1.0
 */
import React, {ReactNode,lazy} from "react";
import {
    UserOutlined,
    ApartmentOutlined
} from '@ant-design/icons';

const Login = lazy(()=>import("../pages/login"));
const Company = lazy(()=>import("../pages/company"));
const User = lazy(()=>import("../pages/users"));
const AdminUser = lazy(()=>import("../pages/admin_user/index"));
const Page404 = lazy(()=>import("../pages/error/Page404"));

export interface IRoute {
    id: string,
    exact?: boolean
    path: string
    title: string
    parentId?: number
    isMenu?: number
    component?: ReactNode
    children?: IRoute[]
    redirect?: string
    icon?: ReactNode
}

export const router :Array<IRoute> = [
    {
        id  : 'user',
        exact: true,
        path :'/admin/user',
        title: '用户管理',
        component : <User/>,
        icon:<UserOutlined/>
    },
    {
        id  : 'company',
        exact: true,
        path :'/admin/company',
        title: '企业管理',
        component : <Company/>,
        icon:<ApartmentOutlined/>
    },
    {
        id  : 'adminUser',
        exact: true,
        path :'/admin/sys_user',
        title: '系统用户管理',
        component : <AdminUser/>,
        icon:<ApartmentOutlined/>
    }
];

export const unAuthRoute = [
    {
        id  : '0-0',
        exact: true,
        path :'/login',
        title: '登录',
        component : <Login/>
    },
    {
        id  : '404',
        path :'*',
        title: '404',
        component : <Page404/>
    }
];
