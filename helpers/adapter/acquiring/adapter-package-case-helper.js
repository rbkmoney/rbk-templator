module.exports = function (bankName) {
    return "adapter." + bankName.toLowerCase().trim().replace(/[^a-zA-Z]/g, "");
};
