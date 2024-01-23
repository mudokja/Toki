import { localaxios } from "./axios"
const local=localaxios();
async function commonaxios(success,fail){
await local.get("").then(success).catch(fail);
console.log(local.get(""))
}export{
    commonaxios,
}