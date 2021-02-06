const assert = require('assert');
const { Given, When, Then, Before } = require('@cucumber/cucumber');
const wdio = require("webdriverio");
const opts = {
  path: '/wd/hub',
  port: 4723,
  capabilities: {
    platformName: "Android",
    platformVersion: "8",
    deviceName: "Android Emulator",
    app: "../../../../../assets/ApiDemos-debug.apk",
    appPackage: "com.android.calculator2",
    appActivity: ".view.TextFields",
    automationName: "UiAutomator2"
  }
};

Before({tags: '@mobile and @calculator'}, async function() {
  const client = await wdio.remote(opts);
})

After({tags: '@mobile and @calculator'}, async function() {
  await client.deleteSession();
})

When('somados os números {int} e {int}', function (int, int2) {

  const calculator = await client.$('//android.widget.Button')
   return wdio;
 });