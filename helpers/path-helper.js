let path = require('path');

module.exports = function (dir) {
    return path.join(process.cwd(), dir, "/");
};
