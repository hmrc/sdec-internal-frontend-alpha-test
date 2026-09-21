#!/usr/bin/env bash

DEFAULT_BROWSER=chrome
BROWSER=$1
ENVIRONMENT=$2
HEADLESSFLAG=$3
PREVIOUSVERSIONFLAG=$4

if [ -z "$BROWSER" ]; then
    echo "BROWSER_TYPE value not set, defaulting to $DEFAULT_BROWSER..."
    echo ""
fi

sbt scalafmtAll scalafmtCheckAll scalafmtSbtCheck clean -Dbrowser="${BROWSER:=chrome}" -Denvironment="${ENVIRONMENT:=local}" -Dbrowser.option.headless="${HEADLESSFLAG:=true}" -Dbrowser.usePreviousVersion="${PREVIOUSVERSIONFLAG:=true}" "testOnly uk.gov.hmrc.ui.specs.* -- -n AcceptanceTests" testReport
