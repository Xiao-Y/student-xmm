/**
 * 检查输入的是否为整数
 * @param field
 */
function checkNumberInput() {
    if (!(event.keyCode == 46) && !(event.keyCode == 8) && !(event.keyCode == 37) && !(event.keyCode == 39)) {
        if (!((event.keyCode >= 48 && event.keyCode <= 57) || (event.keyCode >= 96 && event.keyCode <= 105))) {
            event.returnValue = false;
        }
    }
}

/**
 * 金额格式化
 * @param s 原始数据
 * @param n 小数点位数
 * @author liuyongtao
 * @returns {String}
 */
function fmoney(s, n) {
    n = n > 0 && n <= 20 ? n : 2;
    s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
    var l = s.split(".")[0].split("").reverse(),
        r = s.split(".")[1];
    t = "";
    for (var i = 0; i < l.length; i++) {
        t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
    }
    return t.split("").reverse().join("") + "." + r;
}

/**
 * 金额格式化后还原为数字
 * @param s 格式化后的金额
 * @author liuyongtao
 * @returns
 */
function rmoney(s) {
    s = s + "";
    if (s == "" || s == "undefined" || typeof(s) == "undefined") {
        return 0;
    }
    return parseFloat(s.replace(/[^\d\.-]/g, ""));
}