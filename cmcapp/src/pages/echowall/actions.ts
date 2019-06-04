import { getRequest, postRequest, putRequest } from '../../utils/request'

export async function getEchoWall(id: number) {
  return await getRequest('/echowall/' + id)
}

export async function addAnswer (id: number, context: string) {
  await postRequest('/echowall/' + id, {context})
}

export async function newQuestion (title: string, context: string, tags: string[]) {
  await putRequest('/echowall', {title, context, tags})
}
