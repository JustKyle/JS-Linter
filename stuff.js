
function p(a, b, c) {
    var k = b
    var i = 0
    var j = 1
    while (b + j <= c) {
        if (a[b+j] < a[k]) {
            i = i + 1;
            var t = a[b+i]
            a[b+i] = a[b+j]
            a[b+j] = t
        }
        j = j + 1
    }
    var t = a[k]
    a[k] = a[b+i]
    a[b+i] = t
    return b+i
}

function s(a, b, c) {
    if (b >= c) {
        return a
    }
    var i = p(a, b, c)
    s(a, b, i-1)
    s(a, i+1, c)
    return a
}

var a = [4,3,2,1]
console.log(a)
a = s(a, 0, 3)
console.log(a)
