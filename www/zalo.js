/* JS interface file to define functions for the Plugin */

var argscheck = require('cordova/argscheck'),
    utils = require('cordova/utils'),
    exec = require('cordova/exec');

var PLUGIN_NAME = 'Zalo';
var Zalo = function() {};

function isFunction(obj) {
  return !!(obj && obj.constructor && obj.call && obj.apply);
};

Zalo.test = function(str, callback) {
    cordova.exec(callback, function(err) {
        callback('Nothing to echo.');
    }, 'Zalo', 'echo', [str]);
};

module.exports = CameraPreview;