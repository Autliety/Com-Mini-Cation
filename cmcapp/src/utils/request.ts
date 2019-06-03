import Taro from '@tarojs/taro'

type Method = 'GET' | 'POST' | 'PUT' | 'DELETE'

const solveUrl = (path: string): string => {
  if (path[0] !== '/') {
    path = '/' + path
  }
  return 'http://47.100.102.2:1226' + path
}

async function cmcRequest (method: Method, url: string, payload: object) {

  const token = Taro.getStorageSync('token')
  const header = token ? {
    Authorization: token
  } : undefined

  const option = {
    url,
    method,
    header,
    data: payload
  }

  try {
    const res = await Taro.request(option)
    const {statusCode, data} = res
    if (statusCode >= 300) {
      console.warn('request exception', res)
      // TODO request exception handler
    }
    return data
  } catch (e) {
    // TODO request error handler
    console.error('request error', e)
    throw e
  }

}

export async function postRequest (path: string, payload: object) {
  try {
    return await cmcRequest('POST', solveUrl(path), payload)
  } catch (e) {
    throw e
  }
}

export async function getRequest (path: string, params: object = {}) {
  try {
    return await cmcRequest('GET', solveUrl(path), params)
  } catch (e) {
    throw e
  }
}

export async function downloadRequest (path: string) {

  const token = Taro.getStorageSync('token')
  const header = token ? {cmcToken: token} : undefined

  const option = {
    url: solveUrl(path),
    header
  }
  const res = await Taro.downloadFile(option)
  return res.tempFilePath
}
