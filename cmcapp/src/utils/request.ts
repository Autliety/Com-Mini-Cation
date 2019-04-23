import Taro from '@tarojs/taro'

type Data = object
type Method = 'PUT' | 'GET' | 'DELETE' | 'POST'

const ADDR = 'localhost:8080'

export default async function cmcRequest (method: Method, path: string, payload: Data) {

  if (path[0] !== '/') {
    path = '/' + path
  }
  const option = {
    url: 'http://' + ADDR + path,
    method,
    data: payload
  }
  try {
    const res = await Taro.request(option)
    return responseHandler(res)
  } catch (e) {
    // TODO: request failed handler
    throw e
  }

}

function responseHandler (res) {
  const {data, statusCode} = res
  switch (statusCode) {
    case 200:
      return data
    default:
      // TODO: error handler
      return data
  }
}
