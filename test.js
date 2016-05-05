var  casper= require('casper').create();
var  url=casper.cli.get(0);
var  charset=casper.cli.get('charset');
var  isaction=casper.cli.get('action');
phantom.outputEncoding=charset;
var  pagecontent=true;
var  checktimeout=6000;
if(casper.cli.has('checktimeout'))
{
      checktimeout=casper.cli.get('checktimeout');
}
casper.start(url);
casper.then(function() {
         if(pagecontent)
         {
              this.echo(this.getPageContent());
         }
});
casper.run();
