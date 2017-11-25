/*
 * This is sample code generated by rpcgen.
 * These are only templates and you can use them
 * as a guideline for developing your own functions.
 */

#include "rdict.h"

char	dict[DICTSIZ][MAXWORD+1];/* storage for a dictionary of words	*/
int	nwords = 0;

int *
initw_1_svc(void *argp, struct svc_req *rqstp)
{
	static int  result;

	nwords = 0;
	result =  1;

	return &result;
}

int *
insertw_1_svc(char **argp, struct svc_req *rqstp)
{
	static int  result;
	printf("\nMot à inseré ===== %s\n",*argp);
	if (*lookupw_1_svc(argp, rqstp)==0){
	strcpy(dict[nwords], *argp);
	nwords++;
	result = 1;
	}
	else {
		result = 0;
	}
	return &result;
}

int *
deletew_1_svc(char **argp, struct svc_req *rqstp)
{
	static int  result;

	int	i;
	printf("MOT ===== %s",*argp);
	for (i=0 ; i<nwords ; i++)
		if (strcmp(*argp, dict[i]) == 0) {
			nwords--;
			strcpy(dict[i], dict[nwords]);
			result = 1;
			return &result;
		}
	result = 0;
	return &result;
}

int *
lookupw_1_svc(char **argp, struct svc_req *rqstp)
{
	static int  result;
	int	i;
	printf("\nMot à trouver ===== %s\n",*argp);
	for (i=0 ; i<nwords ; i++){
		if (strcmp(*argp, dict[i]) == 0){
			result = 1;
			return &result;
		}
		}
	result =  0;
	return &result;
}

