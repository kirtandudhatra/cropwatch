var config = {
    apiKey: "AIzaSyBY0YIa_TxCNvZTg9_oIpbnXmCui_ltiyM",
    authDomain: "logindemo-7bbd7.firebaseapp.com",
    databaseURL: "https://logindemo-7bbd7.firebaseio.com",
    projectId: "logindemo-7bbd7",
    storageBucket: "logindemo-7bbd7.appspot.com",
    messagingSenderId: "191887064011"
  };
  firebase.initializeApp(config);


  var ref=firebase.database().ref('data');

  document.getElementById('form1').addEventListener('submit',submitForm);

function submitForm(e){
	e.preventDefault();
	//value
	var question=getInput('question');
	var option1=getInput('option1');
	var option2=getInput('option2');
	var option3=getInput('option3');
	var option4=getInput('option4');
	var correct=getInput('correct');
	var level=getInput('level');
	var subject=getInput('subject');
	var topic=getInput('topic');
	var branch=getInput('branch');

	saveData(question,option1,option2,option3,option4,correct,subject,branch,topic,level);
	document.querySelector('.a').style.display='block';
	var temp=setTimeout(function(){
		document.querySelector('.a').style.display='none';
	},2000);
	document.getElementById('form1').reset();
}


function getInput(id){
	return document.getElementById(id).value;

}

function saveData(question,option1,option2,option3,option4,correct,subject,branch,topic,level){
	var newRef=ref.push();
	newRef.set({
		question:question,option1:option1,option2:option2,option3:option3,option4:option4,correct:correct,subject:subject,branch:branch,topic:topic,level:level
	});
}
