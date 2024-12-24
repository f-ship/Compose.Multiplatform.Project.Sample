const {google} = require('googleapis');
const fs = require('fs');
const {exec} = require('child_process');
const util = require('util');
const execAsync = util.promisify(exec);
const { GoogleAuth, Impersonated } = require('google-auth-library');
const path = require('path');

async function getAccessToken() {
    try {
        console.log('GOOGLE_APPLICATION_CREDENTIALS:', process.env.GOOGLE_APPLICATION_CREDENTIALS);

        const auth = await google.auth.getClient({
            scopes: ['https://www.googleapis.com/auth/androidpublisher'],
        });

        return google.androidpublisher({ version: 'v3', auth });

    } catch (error) {
        console.error('Detailed auth error:', {
            message: error.message,
            details: error.response?.data || error.response,
            stack: error.stack
        });
        throw error;
    }
}

async function uploadToPlayStore() {
    try {
        const androidPublisher = await getAccessToken()

        // const androidPublisher = google.androidpublisher({
        //     version: 'v3',
        //     auth: await getAccessToken()
        // });
        //
        const packageName = process.env.PACKAGE_NAME;
        const aabPath = process.env.AAB_PATH;

        // Get the absolute path to the project root
        const projectRoot = process.cwd();
        console.log('Current working directory:', projectRoot);

        const composeAppPath = path.join(projectRoot, "../../../",aabPath);
        console.log('Checking directory:', composeAppPath);

        try {
            const files = fs.readdirSync(composeAppPath, { recursive: true });
            console.log('Available files in build directory:', files);
        } catch (e) {
            console.log('Error reading build directory:', e.message);
        }


        // Create edit
        const edit = await androidPublisher.edits.insert({
            packageName: packageName
        });

        const editId = edit.data.id;

        // Upload AAB
        const aabContent = fs.readFileSync(composeAppPath);
        const upload = await androidPublisher.edits.bundles.upload({
            packageName: packageName,
            editId: editId,
            media: {
                mimeType: 'application/octet-stream',
                body: aabContent
            }
        });

        // Create track update
        await androidPublisher.edits.tracks.update({
            packageName: packageName,
            editId: editId,
            track: 'internal',
            requestBody: {
                track: 'internal',
                releases: [{
                    versionCodes: [upload.data.versionCode],
                    status: 'draft'
                }]
            }
        });

        // // Commit the edit
        // await androidPublisher.edits.commit({
        //     packageName: packageName,
        //     editId: editId
        // });

        console.log('Successfully uploaded to Play Store!');

        // Step 2: Promote to complete immediately not until the app is non draft and has been reviewed
        // await androidPublisher.edits.tracks.update({
        //     packageName,
        //     editId,
        //     track: 'internal',
        //     requestBody: {
        //         releases: [{
        //             name: 'CI Release',
        //             status: 'completed',
        //             versionCodes: [upload.data.versionCode],
        //         }],
        //     },
        // });

        // Commit the edit (publishes everything)
        await androidPublisher.edits.commit({ packageName, editId });

        console.log('✅ Upload + publish completed!');
    } catch (error) {
        console.error('Error uploading to Play Store:', error.message);
        if (error.response) {
            console.error('Response data:', error.response.data);
        }
        process.exit(1);
    }
}

uploadToPlayStore();