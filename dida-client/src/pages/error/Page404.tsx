import React from 'react'
import {withRouter} from 'react-router-dom'
import {Button, Result} from "antd";
import '../../assets/css/error.css'


function Page404(props:any) {

    function backHome  (){
        props.history.push('/')
    }

    return (
        <Result
            status="404"
            title="404"
            subTitle="很抱歉，你反问的页面不存在！"
            extra={<Button type="primary" onClick={backHome}>回到首页</Button>}
        />
    )
}

export default withRouter(Page404)
