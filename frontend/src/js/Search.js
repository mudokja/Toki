import { localaxios } from "./BaseAxios";

const local = localaxios();

async function roomSearchByTag(tagName, success, fail) {
    await local.get(`/api/v1/rooms/search?tag=${tagName}`)
        .then(success)
        .catch(fail)
}

async function roomSearchByCategory(category, success, fail) {
    await local.get(`/api/v1/search?category=${category}`)
        .then(success)
        .catch(fail)
}

export {
    roomSearchByTag,
    roomSearchByCategory,
}