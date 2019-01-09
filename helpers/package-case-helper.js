module.exports = function (name) {
    return name.toLowerCase().trim()
        .replace(/[^a-zA-Z]/g, "");
};
