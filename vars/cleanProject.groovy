def call(Map projectDetails)
{
 echo "Cleaning up and preparing for project :- ${projectDetails.projectName}"
 
 echo "Pull source code from Repository"
 git branch: 'projectDetails.projectBranch', credentialsId: 'projectDetails.gitCredential', url: 'projectDetails.gitPath'
}
