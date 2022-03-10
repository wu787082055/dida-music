/**
 * @Description: -TODO
 * @Author ZhangWei
 * @Date   2021/6/28 15:39
 * @Version V1.0
 */
import React, {Fragment, ReactNode, Suspense} from 'react';
import {HashRouter, Redirect, Route, Switch} from 'react-router-dom';
import {IRoute, router, unAuthRoute} from "../router";
import AppLayout from "../component/AppLayout";

function generateRoute(routerList:Array<IRoute>) :ReactNode{
    return (
        <Fragment>
            {
                routerList.map(item=>{
                    if (item.children){
                        return (
                            <Fragment>{ generateRoute(item.children)}</Fragment>
                        )
                    }else{
                        return (
                            <Route exact={item.exact} path={item.path} key={item.id}>{item.component}</Route>
                        )
                    }
                })
            }
        </Fragment>
    );
}

export default function Views() {
  return (
      <Fragment>
        <HashRouter>
            <Suspense fallback={<></>}>
                <Switch>
                    <Route path={'/'} exact>
                        <Redirect to={'/admin/user'}/>
                    </Route>

                    <Route path={'/admin'}>
                        <AppLayout>
                            {
                                generateRoute(router)
                            }
                        </AppLayout>
                    </Route>

                    <Switch>
                        {
                            unAuthRoute.map((item: IRoute) => (
                                <Route exact={item.exact} path={item.path} key={item.id}>{item.component}</Route>
                            ))
                        }
                    </Switch>

                </Switch>
            </Suspense>
        </HashRouter>
      </Fragment>
  );
};
