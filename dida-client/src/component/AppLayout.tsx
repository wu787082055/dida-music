/**
 * @Description: -TODO
 * @Author ZhangWei
 * @Date   2021/6/4 21:05
 * @Version V1.0
 */
import React, { useState} from 'react';
import { Layout } from 'antd';
import LeftMenu from "./LeftMenu";

const { Header, Content, Sider } = Layout;


export default function AppLayout(props:any) {

  const [collapsed,setCollapsed] = useState(false);


  function onCollapse() {
    setCollapsed(!collapsed)
  }

  return (
      <Layout style={{ minHeight: '100vh' ,padding:0}}>
        <Sider collapsible collapsed={collapsed} onCollapse={onCollapse} >
         <LeftMenu/>
        </Sider>
        <Layout className="site-layout" >
          <Header className="site-layout-background" style={{
              height:'50px',position: 'fixed',
              zIndex: 1,width:'100%'
          }} />
          <Content style={{ margin: '50px 8px 0 16px' }}>
            <div style={{backgroundColor:'white',marginTop:'15px'}}>
                {props.children}
            </div>
          </Content>

        </Layout>
      </Layout>
  );
}
