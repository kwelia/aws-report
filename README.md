# aws-report

Sends an e-mail report describing AWS service statuses.

Right now it just emails a quick and dirty summary of running and stopped EC2
servers, but might be useful to somebody else someday.

It's just a script intended to be scheduled using cron or similar to keep
stakeholders apprised of EC2 server statuses.  It could easily be forked to
report something else in similar fashion.

## Usage

`lein run [from-address] [to-address ...]` sends the e-mail from from-address to
the provided list of to-address email addresses, using the
[provided AWS credentials](http://docs.aws.amazon.com/AWSJavaSDK/latest/javadoc/com/amazonaws/auth/DefaultAWSCredentialsProviderChain.html)
and `~/.secrets/smtp.clj`

`~/.secrets/smtp.clj`:

```
{:host "smtp.gmail.com"
 :user "username"
 :pass "password"
 :port 465
 :ssl true}
```

## License

Copyright © 2015 Kwelia, Inc.

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
