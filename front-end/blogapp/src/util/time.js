export function formatTime(time) {
  const dt = new Date(time)
  const now = Date.now()

  const diff = (now - dt) / 1000

  if (diff < 30) {
    return '刚刚'
  } else if (diff < 3600) { // less 1 hour
    return Math.ceil(diff / 60) + '分钟前'
  } else if (diff < 3600 * 24) {
    return Math.ceil(diff / 3600) + '小时前'
  } else if (diff < 3600 * 24 * 2) {
    return '1天前'
  }



  const y=dt.getFullYear();
  const m=(dt.getMonth()+1).toString().padStart(2,'0');
  const d=dt.getDate().toString().padStart(2,'0');
  // pad用于补位
  const h=dt.getHours().toString().padStart(2,'0');
  const minute=dt.getMinutes().toString().padStart(2,'0');
  const s=dt.getSeconds().toString().padStart(2,'0');
  return  `${y}-${m}-${d} ${h}:${minute}:${s}`
}
