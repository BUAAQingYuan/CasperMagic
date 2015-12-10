var  casper= require('casper').create();
var  url=casper.cli.get(0);
var  charset=casper.cli.get('charset');
var  isaction=casper.cli.get('action');
phantom.outputEncoding=charset;
var  pagecontent=true;
var  checktimeout=6000
if(casper.cli.has('checktimeout'))
{
      checktimeout=casper.cli.get('checktimeout');
}
casper.start(url);
if(Boolean(isaction))
{
    casper.then(function() {
         this.fill('form#form',{
                 'Login': 'test' ,
                 'Password': '123456' 
         },true);
         this.waitFor(function check(){
             return this.evaluate(function(){
                  return document.querySelectorAll('div#link_list').length > 0;
                          });
         },function then(){
                 pagecontent=true;
         },function timeout(){
                 this.echo('Wait timeout of 7000ms expired. i cannot find the checked tag!');
                 pagecontent=false;
         },checktimeout);
    });
}
casper.then(function() {
         if(pagecontent)
         {
              this.echo(this.getPageContent());
         }
});
casper.run();
