import { localaxios } from "./BaseAxios";

const local=localaxios();
async function commonaxios(success,fail){
await local.get(`api/v1/blacklist`).then(success).catch(fail);
}
async function postaxios(value,success,fail){
    await local.put("/put",JSON.stringify(value)).then(success).catch(fail);
    console.log(JSON.stringify(value),"saf");
    }
export{
await local.get(``).then(success).catch(fail);
}
async function postaxios(value,success,fail){
    await local.post(`/post`,JSON.stringify(value)).then(success).catch(fail);
    console.log(JSON.stringify(value),"saf");
    }
export{
    commonaxios,
    postaxios,
}