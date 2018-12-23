module.exports = function (path) {
    if (path && path.lastIndexOf("/") === (path.length - 1))
        path = path.substring(0, path.lastIndexOf("/"));
    return path ? path + "/" : "";
};
