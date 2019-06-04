import { postRequest } from '../../../../utils/request'

export async function checkAppoint (id: number, yes: boolean) {
  const data = {
    status: yes? 'CONFIRMED' : 'CANCELED'
  }
  await postRequest('/appoint/' + id, data)
}
