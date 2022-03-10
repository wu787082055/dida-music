/**
 * @Description: -TODO
 * @Author ZhangWei
 * @Date   2021/7/13 22:14
 * @Version V1.0
 */

/**
 * 深层复制
 * @param obj
 * @returns {any}
 */
export function deepCopy(obj) {
    return JSON.parse(JSON.stringify(obj))
}
