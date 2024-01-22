$DIST_PATH="$(Get-Location)/../dist"
$TARGET_PATH="$(Get-Location)/../dist"
$HELPER_PATH="$(Get-Location)/helper"

# =============== Prepare service structure ===============
Write-Output "Prepare directory struct"


function deleteIfExist { #это конечно трындец! :)
    param (
        $filePath
    )
    if(Test-Path -Path $filePath) {
        Remove-Item $filePath -Recurse -Force
    }
}

if (Test-Path -PathType Container -Path $DIST_PATH) {
    deleteIfExist $DIST_PATH
    deleteIfExist $TARGET_PATH 
    deleteIfExist config_backend.env
    deleteIfExist config_db.env
    deleteIfExist docker-compose.yml
    deleteIfExist "$(Get-Location)/../logs"
    deleteIfExist "backend/target"
}

if ($args.Count -eq 0) {
    New-Item -Path  "$DIST_PATH/nginx" -ItemType Directory
    New-Item -Path  "$DIST_PATH/flyway_config" -ItemType Directory
    New-Item -Path  "$DIST_PATH/flyway_migration" -ItemType Directory
    New-Item -Path  "$DIST_PATH/import" -ItemType Directory
    New-Item -Path  "$DIST_PATH/pgadmin/storage/root_root.com/" -ItemType Directory
    Write-Output "Created dist directory structure"
    
    Copy-Item -Path "$HELPER_PATH/data_for_import/*" -Destination "$DIST_PATH/import" –Recurse
    Copy-Item -Path "$HELPER_PATH/flyway_migration/*" -Destination "$DIST_PATH/flyway_migration" –Recurse
    Copy-Item -Path "$HELPER_PATH/pgadmin_config/*" -Destination "$DIST_PATH/pgadmin/storage/root_root.com" –Recurse
    Copy-Item -Path "$HELPER_PATH/flyway_config/*" -Destination "$DIST_PATH/flyway_config" –Recurse
    Copy-Item -Path "$HELPER_PATH/nginx/*" -Destination "$DIST_PATH/nginx" –Recurse
    Copy-Item -Path "$HELPER_PATH/configs/*" -Destination $(Get-Location) –Recurse
    Copy-Item -Path "$HELPER_PATH/docker/docker-compose.yml" -Destination $(Get-Location) –Recurse
    Write-Output "Copy file for launch"

    docker-compose down;
    docker-compose up --build
}
