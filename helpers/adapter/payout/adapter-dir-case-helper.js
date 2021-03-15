module.exports = function (bankName) {
    return "adapter/payout/" + bankName.toLowerCase().trim().replace(/[^a-zA-Z]/g, "");
};
