import Taro from '@tarojs/taro'
import { downloadRequest, getRequest } from '../../../../utils/request'

export async function download (id: number): Promise<void> {
  const path = '/selfqna/' + id
  const filePath = await downloadRequest(path)
  await Taro.openDocument({
    filePath,
    fileType: 'docx'
  })
}

export async function search (key: string) {
  const res = await getRequest('/selfqna', {search: key})
  return res.results
}
